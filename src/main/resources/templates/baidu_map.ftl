<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src='http://api.map.baidu.com/api?v=2.0&ak=5d536714b11af985ec23ffe0'></script>
    <script type="text/javascript" src='/js/jquery-3.0.0.min.js'></script>
	<title>百度地图展示</title>
</head>
<body>
<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");    // 创建Map实例
	map.centerAndZoom(new BMap.Point(108.967578, 34.285419), 6);  // 初始化地图,设置中心点坐标和地图级别

	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	map.addControl(new BMap.ScaleControl());
//	map.setCurrentCity("惠州");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放

	var opts = {
		width : 200,     // 信息窗口宽度
		height: 100,     // 信息窗口高度
		title : "" , // 信息窗口标题
		enableMessage:true//设置允许信息窗发送短息
	}

	<#list locations as l>
    //var myIcon = new BMap.Icon("http://developer.baidu.com/map/jsdemo/img/fox.gif", new BMap.Size(300,157));
    //var marker = new BMap.Marker(pt,{icon:myIcon});  // 创建标注
		var marker = new BMap.Marker(new BMap.Point(${l.longitude}, ${l.latitude}));  // 创建标注
		var content = "${l.address}";
		map.addOverlay(marker);               // 将标注添加到地图中
		addClickHandler(content,marker);
	</#list>

	function addClickHandler(content,marker){
		marker.addEventListener("click",function(e){
					openInfo(content,e)}
		);
	}
	function openInfo(content,e){
		var p = e.target;
		var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
		var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
		map.openInfoWindow(infoWindow,point); //开启信息窗口
	}
</script>