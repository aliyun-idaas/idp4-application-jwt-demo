<%--
 * 
 * @author Shengzhao Li
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Index</title>
    <style>

        .ui-appList-box .ui-appList {
            text-decoration: none;
            background-color: #fff;
        }

        .ui-appList-box .ui-appList:hover {
            text-decoration: none;
        }

        .ui-appList-box .ui-appList .ui-appList-text {
            /* border: 1px solid red;*/
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }

        .ui-appList-box .ui-appList .ui-appList-text p {
            margin: 0;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;

        }

        @media (min-width: 768px) {
            .ui-appList-box {
                padding: 0 15px;
                text-align: center;
                margin-bottom: 15px;
            }

            /*布局盒子*/
            .ui-appList-box .ui-appList {
                position: relative;
                display: block;
                height: 72px;
                border: 1px solid #e5e5e5;
                border-radius: 3px;
            }

            .ui-appList-box .ui-appList:hover {
                border-color: #d5d5d5;
            }

            .ui-appList-box .ui-appList .ui-appList-icon,
            .ui-appList-box .ui-appList .ui-appList-text {
                display: block;
                position: absolute;
                top: 0;
                left: 0;
                z-index: 1;
            }

            /*应用图标*/
            .ui-appList-box .ui-appList .ui-appList-icon {
                width: 80px;;
                height: 70px;
                line-height: 60px;
                border-right: 1px solid #e8e8e8;
                padding: 5px;
            }

            .ui-appList-box .ui-appList .ui-appList-icon img {
                max-height: 50px;
                max-width: 50px;
                -webkit-border-radius: 5px;
                border-radius: 5px;
            }

            /*应用文字*/
            .ui-appList-box .ui-appList .ui-appList-text {
                width: 100%;
                height: 70px;
                line-height: 40px;
                padding: 16px 10px 20px 95px;
                text-align: left;
                color: #787878;
                font-size: 14px;
            }

            .ui-appList-box .ui-appList.ui-applist-warning .ui-appList-text {
                height: auto;
                line-height: normal;
            }

            .ui-appList-box .ui-appList .info-box-text-bookmark-user {
                position: absolute;
                top: 40px;
                padding: 0px 10px 0px 95px;
            }

            .ui-appList-box .ui-appList.ui-applist-warning .ui-appList-text p {
                line-height: 19px;
                font-size: 14px;
                padding-left: 0;
                /* border: 1px solid red;*/
            }

            .ui-appList-box .ui-appList.ui-share .ui-appList-text p {
                line-height: 18px;
                font-size: 14px;
                /* border: 1px solid red;*/
            }

            .ui-appList-box .ui-appList.ui-applist-warning .ui-appList-text p.ui-appList-text-errer {
                margin: 0;
                font-size: 12px;
                color: #fcbe32;
            }

            .ui-appList-box .ui-appList.ui-share .ui-appList-text p.ui-appList-text-errer {
                margin: 0;
                font-size: 12px;
                color: #ccc;
            }

            /*应用设置*/
            .ui-appList-box .ui-appList-set {
                position: absolute;
                top: 0;
                right: 0;
                color: #fff;
                z-index: 9;
                cursor: pointer;
            }

            .ui-appList-box .ui-appList-set:hover {

            }

            .ui-appList-box .ui-appList-set:before {
                content: " ";
                display: block;
                position: relative;
                width: 40px;
                height: 40px;
                background-color: transparent;
                z-index: 1;
                border-width: 20px;
                border-style: solid;
                border-color: #40beed #40beed transparent transparent;
            }

            .ui-appList-box .ui-appList.ui-applist-warning .ui-appList-set:before {
                content: " ";
                display: block;
                position: relative;
                width: 40px;
                height: 40px;
                background-color: transparent;
                z-index: 1;
                border-width: 20px;
                border-style: solid;
                border-color: #fcbe32 #fcbe32 transparent transparent;
            }

            .ui-appList-box .ui-appList-set .ui-appList-set-icon {
                position: absolute;
                padding: 0;
                top: -1px;
                right: -3px;
                z-index: 999;
                height: 40px;
                width: 30px;
                font-size: 18px;;
            }
        }

        .ui-appList-box .glyphicon {
            font-size: 12px;
        }

        .login-msg-top {
            display: inline-block;
            width: 100px;
        }
    </style>
</head>
<body>


<div class="row">
    <div class="col-md-12">
        <div class="panel panel-info">
            <div class="panel-heading"> 登录信息</div>
            <div class="panel-body">
                <dl class="dl-horizontal">
                    <p>
                        <span class="login-msg-top">用户名</span>
                        <span>${userProfile.username}</span>
                    </p>
                    <p>
                        <span class="login-msg-top">登录次数</span>
                        <span>${userProfile.loginTimes}</span>
                    </p>


                </dl>
            </div>
        </div>
    </div>
</div>
<script src="${contextPath}/static/js/jquery.min.js"></script>
<script>
    $(function () {
        new indexPage()
    })
</script>
</body>
</html>
