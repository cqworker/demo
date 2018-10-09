<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>首页</title>
    <link rel="icon" href="picture.ico" type="/img/bg.ico"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href=" /layui/css/layui.css">
    <script src="/layui/layui.js"></script>
    <script src="/layui/jquery-1.8.3.min.js"></script>

</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>今日最佳</legend>
</fieldset>
<div style="position:absolute; width:100%; height:100%; left: 40px; top: 75px;">
    <div class="layui-carousel" id="test1" style="">
        <div carousel-item="">
            <div><img src="http://123.207.74.68:801/images/NO2.jpg"/></div>
            <div><img src="http://123.207.74.68:801/images/NO3.jpg"/></div>
            <div><img src="http://123.207.74.68:801/images/NO1.jpg"/></div>
        </div>
    </div>
</div>

<div class="layui-fluid" style="position:absolute; width:100%; height:100%;  left: 0px; top: 450px;">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>精选土鸡</legend>
    </fieldset>
    <div class="layui-row layui-col-space10 demo-list">
    <#list nameList as imgname>
        <div class="layui-col-sm4 layui-col-md3 layui-col-lg2">
            <!-- 填充内容 -->
            <div class="layui-card">
                <div><img src="http://123.207.74.68:801/images/sl/${imgname}" height="230"width="150"/></div>
            </div>
        </div>
    </#list>
    </div>
</div>


<script>
    layui.use(['carousel', 'form'], function(){
        var carousel = layui.carousel
                ,form = layui.form;

        carousel.render({
            elem: '#test1'
            ,interval: 5000
            ,anim: 'fade'
            ,height: '320px'
            ,width: '1200'
        });

    });
</script>



</body>
</html>

