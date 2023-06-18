package com.silver.b_factory;

public class RuleConfig {
}

interface IRuleConfigParser {
    RuleConfig parse(String config);
}

class JsonRuleConfigParser implements IRuleConfigParser{

    @Override
    public RuleConfig parse(String config) {
        return null;
    }
}
class XmlRuleConfigParser implements IRuleConfigParser{

    @Override
    public RuleConfig parse(String config) {
        return null;
    }
}
class YamlRuleConfigParser implements IRuleConfigParser{

    @Override
    public RuleConfig parse(String config) {
        return null;
    }
}
class PropertiesRuleConfigParser implements IRuleConfigParser{

    @Override
    public RuleConfig parse(String config) {
        return null;
    }
}
