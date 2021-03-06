/*
 * Copyright 2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.starter.feature.database;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConfigurationHelper {

    public static final Map<String, Object> JPA_DDL;

    static {
        JPA_DDL = new LinkedHashMap<>();
        JPA_DDL.put("jpa.default.properties.hibernate.hbm2ddl.auto", "update");
    }

    public static Map<String, Object> jdbc(DatabaseDriverFeature dbFeature) {
        final String prefix = "datasources.default.";
        Map<String, Object> jdbcConfig = new LinkedHashMap<>();
        jdbcConfig.put(prefix + "url", dbFeature.getJdbcUrl());
        jdbcConfig.put(prefix + "driverClassName", dbFeature.getDriverClass());
        jdbcConfig.put(prefix + "username", dbFeature.getDefaultUser());
        jdbcConfig.put(prefix + "password", dbFeature.getDefaultPassword());
        return jdbcConfig;
    }
}
