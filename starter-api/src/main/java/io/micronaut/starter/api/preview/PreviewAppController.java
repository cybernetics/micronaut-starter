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
package io.micronaut.starter.api.preview;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.starter.ContextFactory;
import io.micronaut.starter.api.create.CreateOperation;
import io.micronaut.starter.command.CreateAppCommand;
import io.micronaut.starter.command.CreateCommand;
import io.micronaut.starter.feature.validation.FeatureValidator;
import io.micronaut.starter.options.BuildTool;
import io.micronaut.starter.options.Language;
import io.micronaut.starter.options.TestFramework;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Previews an application contents.
 *
 * @author graemerocher
 * @since 1.0.0
 */
@Controller("/preview")
public class PreviewAppController extends AbstractPreviewController {
    public PreviewAppController(
            CreateAppCommand.CreateAppFeatures createAppFeatures,
            FeatureValidator featureValidator,
            ContextFactory contextFactory) {
        super(createAppFeatures, featureValidator, contextFactory);
    }

    @Get(uri = "/app/{name}{?features,lang,build,test}", produces = MediaType.APPLICATION_JSON)
    @ApiResponse(
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON
            )
    )
    @Override
    public Map<String, String> previewApp(String name, @Nullable List<String> features, @Nullable BuildTool build, @Nullable TestFramework test, @Nullable Language lang) throws IOException {
        return super.previewApp(name, features, build, test, lang);
    }

    @Override
    protected CreateCommand buildCommand(Language lang, BuildTool buildTool, TestFramework testFramework, @Nullable List<String> features) {
        return CreateOperation.buildCreateAppCommand(
                (CreateAppCommand.CreateAppFeatures) availableFeatures,
                featureValidator,
                contextFactory,
                features,
                lang,
                buildTool,
                testFramework
        );
    }

}