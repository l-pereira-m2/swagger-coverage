package com.github.viclovsky.swagger.coverage.core.results.builder.core;

import com.github.viclovsky.swagger.coverage.configuration.Configuration;
import com.github.viclovsky.swagger.coverage.configuration.options.ConfigurationOptions;
import com.github.viclovsky.swagger.coverage.core.generator.SwaggerSpecificationProcessor;
import com.github.viclovsky.swagger.coverage.core.model.OperationKey;
import com.github.viclovsky.swagger.coverage.core.model.OperationsHolder;
import com.github.viclovsky.swagger.coverage.core.results.Results;
import com.github.viclovsky.swagger.coverage.core.results.data.OperationResult;
import com.github.viclovsky.swagger.coverage.core.rule.core.ConditionRule;
import io.swagger.v3.oas.models.OpenAPI;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public abstract class StatisticsOperationPostBuilder extends StatisticsPostBuilder {
    protected Map<String, List<String>> operationToTag;

    @Override
    public StatisticsBuilder configure(List<ConditionRule> rules) {
        return this;
    }

    @Override
    public StatisticsBuilder configure(ConfigurationOptions options, OpenAPI swagger) {
        super.configure(options, swagger);

        OperationsHolder operations = SwaggerSpecificationProcessor.extractOperation(swagger, includeTags);

        this.operationToTag = operations.getOperations()
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getTags() != null)
                .collect(toMap(entry -> entry.getKey().toString(), entry -> entry.getValue().getTags()));

        return this;
    }


    @Override
    public void build(Results results, Configuration configuration) {
        results.getOperations().forEach(this::buildOperation);
        buildResult(results);
    }

    public abstract void buildOperation(OperationKey operation, OperationResult operationResult);

    public abstract void buildResult(Results results);
}
