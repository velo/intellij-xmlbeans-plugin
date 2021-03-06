/*
 * #%L
 * XMLBeans integration
 * %%
 * Copyright (C) 2013 - 2014 BSB S.A.
 * %%
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
 * #L%
 */
package com.bsb.intellij.plugins.xmlbeans.facet.ui.validation;

import com.intellij.facet.ui.FacetConfigurationQuickFix;
import com.intellij.facet.ui.FacetValidatorsManager;
import com.intellij.openapi.vfs.VfsUtil;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * @author gja
 * @version $Revision: 2308 $ $Date: 2013-08-19 15:47:35 +0200 (lun., 19 août 2013) $
 */
public class CreateDirectoryQuickFix extends FacetConfigurationQuickFix {

  private final File file;
  private final FacetValidatorsManager manager;
  private final Runnable quickFixCallback;

  public CreateDirectoryQuickFix(File file, FacetValidatorsManager manager, Runnable quickFixCallback) {
    super("Create directory");
    this.file = file;
    this.manager = manager;
    this.quickFixCallback = quickFixCallback;
  }

  @Override
  public void run(JComponent place) {
    try {
      VfsUtil.createDirectories(file.getAbsolutePath());
      this.quickFixCallback.run();
      manager.validate();
    }
    catch (IOException e) {
      throw new IllegalStateException("Could not create directory : " + file.getAbsolutePath(), e);
    }
  }
}
