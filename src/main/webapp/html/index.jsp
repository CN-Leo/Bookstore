<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Document</title>
  <link rel="stylesheet" type="text/css" href="../res/static/css/main.css">
  <link rel="stylesheet" type="text/css" href="../res/layui/css/layui.css">
  <script type="text/javascript" src="../res/layui/layui.js"></script>
  <script type="text/javascript" src="../script/angular-1.7.8.min.js"></script>
  <script type="text/javascript" src="../script/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../script/qm_main.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
</head>
<body id="list-cont" ng-app="mainApp" ng-controller="mainController">
  <div  class="site-nav-bg">
    <div class="site-nav w1200">
      <p class="sn-back-home">
        <i class="layui-icon layui-icon-home"></i>
        <a href="index.html">首页</a>
      </p>
      <div class="sn-quick-menu">
        <div class="login" ng-if="fUserName"><a href="#">{{fUserName}}</a></div>
        <div class="login" ng-if="!fUserName"><a href="login.html">登陆</a></div>
        <div class="sp-cart"><a href="shopcart.html">购物车</a></div>
      </div>
    </div>
  </div>


  <div class="header">
    <div class="headerLayout w1200">
      <div class="headerCon">
        <h1 class="mallLogo">
          <a href="#" title="药品管理">
            <img src="../res/static/img/logo_index.png">
          </a>
        </h1>
        <div class="mallSearch">
          <form action="" class="layui-form" novalidate>
            <input type="text" name="title" required  lay-verify="required" autocomplete="off" class="layui-input" placeholder="请输入需要的书名">
            <button class="layui-btn" lay-submit lay-filter="bookSearch">
                <i class="layui-icon layui-icon-search"></i>
            </button>
            <input type="hidden" name="" value="">
          </form>
        </div>
      </div>
    </div>
  </div>


  <div class="content">
    <div class="main-nav">
      <div class="inner-cont0">
        <div class="inner-cont1 w1200">
          <div class="inner-cont2">
            <a href="commodity.html" class="active">所有商品</a>
            <a href="buytoday.html">今日团购</a>
            <a href="information.html">母婴资讯</a>
            <a href="about.html">关于我们</a>
          </div>
        </div>
      </div>
    </div>
    <div class="category-con">
      <div class="category-inner-con w1200">
        <div class="category-type">
          <h3>全部分类</h3>
        </div>
        <div class="category-tab-content">
          <div class="nav-con">
            <ul class="normal-nav layui-clear">
              <li class="nav-item">
                <div class="title">中药</div>
                <p><a href="#">处方药</a><a href="#">非处方药</a></p>
                <i class="layui-icon layui-icon-right"></i>
              </li>
              <li class="nav-item">
                <div class="title">西药</div>
                 <p><a href="#">处方药</a><a href="#">非处方药</a></p>
                <i class="layui-icon layui-icon-right"></i>
              </li>
              <li class="nav-item">
                <div class="title">复方药</div>
                <p><a href="#">处方药</a><a href="#">非处方药</a></p>
                <i class="layui-icon layui-icon-right"></i>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div class="category-banner">
        <div class="w1200">
          <img src="../res/static/img/banner1.jpg">
        </div>
      </div>
    </div>
    <div class="floors">
      <div class="sk">
        <div class="sk_inner w1200">
          <div class="sk_hd">
            <a href="javascript:;">
              <img src="../res/static/img/s_img1.jpg">
            </a>
          </div>
          <div class="sk_bd">
            <div class="layui-carousel" id="test1">
              <div carousel-item>
                <div class="item-box">
                  <div class="item" ng-repeat="x in drugList">
                    <a href="javascript:;"><img src="../res/static/img/yp1.jpg"></a>
                    <div class="title">{{x.fDrugName}}</div>
                    <div class="price">
                      <span>￥49.00</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>    
      </div>
    </div>

    <div class="product-cont w1200" id="product-cont">
      <div class="product-item product-item1 layui-clear">
        <div class="left-title">
          <h4><i>1F</i></h4>
          <img src="../res/static/img/icon_gou.png">
          <h5>中药</h5>
        </div>
        <div class="right-cont">
          <a href="javascript:;" class="top-img"><img src="../res/static/img/img12.jpg" alt=""></a>
          <div class="img-box">
            <a href="javascript:;"><img src="../res/static/img/s_img7.jpg"></a>
            <a href="javascript:;"><img src="../res/static/img/s_img8.jpg"></a>
            <a href="javascript:;"><img src="../res/static/img/s_img9.jpg"></a>
            <a href="javascript:;"><img src="../res/static/img/s_img10.jpg"></a>
            <a href="javascript:;"><img src="../res/static/img/s_img11.jpg"></a>
          </div>
        </div>
      </div>
      <div class="product-item product-item2 layui-clear">
        <div class="left-title">
          <h4><i>2F</i></h4>
          <img src="../res/static/img/icon_gou.png">
          <h5>西药</h5>
        </div>
        <div class="right-cont">
          <a href="javascript:;" class="top-img"><img src="../res/static/img/img12.jpg" alt=""></a>
          <div class="img-box">
            <a href="javascript:;"><img src="../res/static/img/s_img7.jpg"></a>
            <a href="javascript:;"><img src="../res/static/img/s_img8.jpg"></a>
            <a href="javascript:;"><img src="../res/static/img/s_img9.jpg"></a>
            <a href="javascript:;"><img src="../res/static/img/s_img10.jpg"></a>
            <a href="javascript:;"><img src="../res/static/img/s_img11.jpg"></a>
          </div>
        </div>
      </div>
      <div class="product-item product-item3 layui-clear">
        <div class="left-title">
          <h4><i>3F</i></h4>
          <img src="../res/static/img/icon_gou.png">
          <h5>复方药</h5>
        </div>
        <div class="right-cont">
          <a href="javascript:;" class="top-img"><img src="../res/static/img/img12.jpg" alt=""></a>
          <div class="img-box">
            <a href="javascript:;"><img src="../res/static/img/s_img7.jpg"></a>
            <a href="javascript:;"><img src="../res/static/img/s_img8.jpg"></a>
            <a href="javascript:;"><img src="../res/static/img/s_img9.jpg"></a>
            <a href="javascript:;"><img src="../res/static/img/s_img10.jpg"></a>
            <a href="javascript:;"><img src="../res/static/img/s_img11.jpg"></a>
          </div>
        </div>
      </div>
    </div>

    <div class="product-list-box" id="product-list-box">
      <div class="product-list-cont w1200">
        <h4>更多推荐</h4>
        <div class="product-item-box layui-clear">
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more1.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more2.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more3.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more1.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more2.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more3.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more1.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more2.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more3.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more1.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more2.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more3.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more1.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more2.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more3.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more1.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more2.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more3.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more1.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more2.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more3.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more1.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more2.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more3.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more1.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more2.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
          <div class="list-item">
            <a href="javascript:;"><img src="../res/static/img/more3.jpg"></a>
            <p>时尚宝宝小黄鸭T恤纯棉耐脏多色可选0-2岁宝宝</p>
            <span>￥100.00</span>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="footer">
    <div class="ng-promise-box">
      <div class="ng-promise w1200">
        <p class="text">
          <a class="icon1" href="javascript:;">7天无理由退换货</a>
          <a class="icon2" href="javascript:;">满99元全场免邮</a>
          <a class="icon3" style="margin-right: 0" href="javascript:;">100%品质保证</a>
        </p>
      </div>
    </div>
    <div class="mod_help w1200">                                     
      <p>
        <a href="javascript:;">关于我们</a>
        <span>|</span>
        <a href="javascript:;">帮助中心</a>
        <span>|</span>
        <a href="javascript:;">售后服务</a>
        <span>|</span>
        <a href="javascript:;">母婴资讯</a>
        <span>|</span>
        <a href="javascript:;">关于货源</a>
      </p>
      <p class="coty">母婴商城版权所有 &copy; 2012-2020</p>
    </div>
  </div>
  <script type="text/javascript">

  var mainApp = angular.module("mainApp", []);
  mainApp.controller("mainController", function($scope,$http,$filter) {
     $scope.fUserName = "${sessionScope.user_info.fUserName}";
     $scope.fUserCode = "${sessionScope.user_info.fUserCode}";
     $http({
    	    method: 'GET',
    	    url: '/manager/drug/page?pageSize=5&pageNumber=1'
    	}).then(function successCallback(response) {
    		$scope.drugList= response.data.rows;
    	    }, function errorCallback(response) {
    	    	 alert(response);
    	});
  });


 
layui.config({
    base: '../res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录
  }).use(['mm','carousel'],function(){
	  
      var carousel = layui.carousel,
     mm = layui.mm;
     var option = {
        elem: '#test1'
        ,width: '100%' //设置容器宽度
        ,arrow: 'always'
        ,height:'298' 
        ,indicator:'none'
      }
      carousel.render(option);
});
  </script>
</body>
</html>