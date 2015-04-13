/*
 * Copyright 2014 asikprad.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.theothercompany.theothertodolist.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller used to showcase what happens when an exception is thrown
 *
 * @author asikprad
 * <p/>
 * Also see how the bean of type 'SimpleMappingExceptionResolver' has been
 * declared inside /WEB-INF/mvc-core-config.xml
 */
@Controller
public class CrashController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrashController.class);

    @RequestMapping(value = "/oups", method = RequestMethod.GET)
    public String triggerException() {
        return "exception";
    }
    
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String triggerResouceNotFound() {
        return "resourcenotfound";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        LOGGER.error(" Exception", ex);
        return "uncaughtException";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(Exception ex) {
        LOGGER.error(" Runtime", ex);
        return "uncaughtException";
    }

}
