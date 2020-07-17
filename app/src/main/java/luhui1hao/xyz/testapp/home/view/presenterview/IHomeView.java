package luhui1hao.xyz.testapp.home.view.presenterview;

import luhui1hao.xyz.testapp.common.base.IView;
import luhui1hao.xyz.testapp.home.model.entity.HomeData;

public interface IHomeView extends IView {
    void loadData(HomeData data);
}
