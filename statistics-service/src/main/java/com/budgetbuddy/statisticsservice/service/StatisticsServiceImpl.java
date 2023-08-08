package com.budgetbuddy.statisticsservice.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.budgetbuddy.statisticsservice.domain.Account;
import com.budgetbuddy.statisticsservice.domain.Currency;
import com.budgetbuddy.statisticsservice.domain.Item;
import com.budgetbuddy.statisticsservice.domain.Saving;
import com.budgetbuddy.statisticsservice.domain.TimePeriod;
import com.budgetbuddy.statisticsservice.domain.timeseries.DataPoint;
import com.budgetbuddy.statisticsservice.domain.timeseries.DataPointId;
import com.budgetbuddy.statisticsservice.domain.timeseries.ItemMetric;
import com.budgetbuddy.statisticsservice.domain.timeseries.StatisticMetric;
import com.budgetbuddy.statisticsservice.repository.DataPointRepository;
import com.google.common.collect.ImmutableMap;

@Service
public class StatisticsServiceImpl implements StatisticsService{

    @Autowired
    private DataPointRepository repository;

    @Autowired
    private ExchangeRatesService ratesService;

    @Override
    public List<DataPoint> findByAccountName(String accountName) {
        return repository.findByIdAccount(accountName);
    }

    @Override
	public DataPoint save(String accountName, Account account) {

		Instant instant = LocalDate.now().atStartOfDay()
				.atZone(ZoneId.systemDefault()).toInstant();

		DataPointId pointId = new DataPointId(accountName, Date.from(instant));

		Set<ItemMetric> incomes = account.getIncomes().stream()
				.map(this::createItemMetric)
				.collect(Collectors.toSet());

		Set<ItemMetric> expenses = account.getExpenses().stream()
				.map(this::createItemMetric)
				.collect(Collectors.toSet());

		Map<StatisticMetric, BigDecimal> statistics = createStatisticMetrics(incomes, expenses, account.getSaving());

		DataPoint dataPoint = new DataPoint();
		dataPoint.setId(pointId);
		dataPoint.setIncomes(incomes);
		dataPoint.setExpenses(expenses);
		dataPoint.setStatistics(statistics);
		dataPoint.setRates(ratesService.getCurrentRates());

		return repository.save(dataPoint);
	}

	private Map<StatisticMetric, BigDecimal> createStatisticMetrics(Set<ItemMetric> incomes, Set<ItemMetric> expenses, Saving saving) {

		BigDecimal savingAmount = ratesService.convert(saving.getCurrency(), Currency.getBase(), saving.getAmount());

		BigDecimal expensesAmount = expenses.stream()
				.map(ItemMetric::getAmount)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		BigDecimal incomesAmount = incomes.stream()
				.map(ItemMetric::getAmount)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		return ImmutableMap.of(
				StatisticMetric.EXPENSES_AMOUNT, expensesAmount,
				StatisticMetric.INCOMES_AMOUNT, incomesAmount,
				StatisticMetric.SAVING_AMOUNT, savingAmount
		);
	}

	private ItemMetric createItemMetric(Item item) {

		BigDecimal amount = ratesService
				.convert(item.getCurrency(), Currency.getBase(), item.getAmount())
				.divide(item.getPeriod().getBaseRatio(), 4, RoundingMode.HALF_UP);

		return new ItemMetric(item.getTitle(), amount);
	}
}
