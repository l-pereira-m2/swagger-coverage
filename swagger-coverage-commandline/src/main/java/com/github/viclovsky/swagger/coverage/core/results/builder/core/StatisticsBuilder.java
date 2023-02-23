package com.github.viclovsky.swagger.coverage.core.results.builder.core;

import com.github.viclovsky.swagger.coverage.configuration.Configuration;
import com.github.viclovsky.swagger.coverage.configuration.options.ConfigurationOptions;
import com.github.viclovsky.swagger.coverage.core.results.Results;
import com.github.viclovsky.swagger.coverage.core.rule.core.ConditionRule;
import io.swagger.v3.oas.models.OpenAPI;

import java.util.List;

public abstract class StatisticsBuilder {

    protected ConfigurationOptions options;
    protected OpenAPI swagger;
    protected List<String> includeTags;

    public StatisticsBuilder add(String path) {
        return this;
    }

    public StatisticsBuilder add(OpenAPI swagger) {
        return this;
    }

    public StatisticsBuilder configure(ConfigurationOptions options, OpenAPI swagger) {
        this.options = options;
        this.swagger = swagger;
        if (this.options.getRules() != null && this.options.getRules().get("include-tags") != null) {
            includeTags = this.options.getRules().get("include-tags").getTags();
        }
        return this;
    }

    public abstract StatisticsBuilder configure(List<ConditionRule> rules);

    public abstract void build(Results results, Configuration configuration);

    public abstract boolean isPreBuilder();

    public abstract boolean isPostBuilder();
}
