<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>腾讯地图</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <style type="text/css">
        html,body{
            width:100%;
            height:100%;
        }
        *{
            margin:0px;
            padding:0px;
        }
        body, button, input, select, textarea {
            font: 12px/16px Verdana, Helvetica, Arial, sans-serif;
        }
        p{
            width:603px;
            padding-top:3px;
            overflow:hidden;
        }
        .btn{
            width:142px;
        }
        #container{
            min-width:600px;
            min-height:950px;
        }
    </style>
    <script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>
    <script>

        window.onload = function(){//直接加载地图

            //初始化地图函数  自定义函数名init
            function init() {
                //定义map变量 调用 qq.maps.Map() 构造函数   获取地图显示容器
                var map = new qq.maps.Map(document.getElementById("container"), {
                    center: new qq.maps.LatLng(34.285419,108.967578),      // 地图的中心地理坐标。
                    zoom:5                                          // 地图的中心地理坐标。
                });

                var scaleControl = new qq.maps.ScaleControl({
                    align: qq.maps.ALIGN.BOTTOM_LEFT,
                    margin: qq.maps.Size(85, 15),
                    map: map
                });

                var info = new qq.maps.InfoWindow({
                    map: map
                });

				<#list locations as l>
					var marker = new qq.maps.Marker({
						position: new qq.maps.LatLng(${l.latitude},${l.longitude}),
						map: map
					});
                    addClickHandler('${l.address}',marker);
				</#list>

                function addClickHandler(content,marker){
                    qq.maps.event.addListener(marker, 'click', function() {
                        info.open();
                        info.setContent('<div style="text-align:center;white-space:nowrap;margin:10px;">'+content+'</div>');
                        info.setPosition(marker.getPosition());
                    });
                }
            }
            //调用初始化函数地图
            init();
        }
    </script>
</head>
<body>
<!--   定义地图显示容器   -->
<div id="container"></div>
</body>
</html>
