/*
 * Copyright 2016, The Android Open Source Project
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

package luhui1hao.xyz.testapp.common.base;

public interface IView {

//    void setPresenter(T presenter);

    //显示加载进度条
    public void showLoading();
    //隐藏加载进度条
    public void hideLoading();
    //显示错误信息
    public void showErrorMsg(String errorMsg);
}
