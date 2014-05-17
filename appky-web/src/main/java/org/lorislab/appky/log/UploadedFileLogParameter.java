/*
 * Copyright 2012 Andrej Petras <andrej@ajka-andrej.com>.
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
package org.lorislab.appky.log;


import java.util.ArrayList;
import java.util.List;
import org.kohsuke.MetaInfServices;
import org.lorislab.jel.log.parameters.ClassLogParameter;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.UploadedFile;


/**
 * The uploaded file logger parameter.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@MetaInfServices
public class UploadedFileLogParameter implements ClassLogParameter {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Class<?>> getClasses() {
        List<Class<?>> result = new ArrayList<>();
        result.add(UploadedFile.class);
        result.add(DefaultUploadedFile.class);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isResult() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getObject(Object parameter) {
        UploadedFile file = (UploadedFile) parameter;
        return file.getClass().getSimpleName() + ":" + file.getFileName();
    }
}
