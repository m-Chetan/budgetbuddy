package com.budgetbuddy.statisticsservice.domain.timeseries;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.budgetbuddy.statisticsservice.domain.Currency;

import lombok.Data;

@Data
@Document(collection = "datapoints")
public class DataPoint {
    @Id
	private DataPointId id;

	private Set<ItemMetric> incomes;

	private Set<ItemMetric> expenses;

	private Map<StatisticMetric, BigDecimal> statistics;

	private Map<Currency, BigDecimal> rates;
}
