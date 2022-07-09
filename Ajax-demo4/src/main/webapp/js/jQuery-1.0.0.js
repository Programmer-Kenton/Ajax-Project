function jQuery(selector){
    // selector可能是#id 也可能是其他的选择器 例如类选择器:.class
    // 根据id获取元素
    if (typeof selector == "string") {
        if (selector.charAt(0) == "#") {
            // selector是一个id选择器
            // var domObj = document.getElementById(selector.substring(1));
            // 升级成全局变量
            domObj = document.getElementById(selector.substring(1));
            // 返回dom对象
            // return domObj;
            // 返回的jQuery对象
            return new jQuery();
        }
    }

    // 页面加载完毕后 注册回调函数
    if (typeof selector == "function") {
        window.onload = selector
    }

    // 定义一个html()函数 代替:domObj.innerHTML = ""
    this.html = function (htmlStr){
        domObj.innerHTML = htmlStr;
    }
    // 定义一个click()函数代替domObj.onclick = function(){}
    this.click = function (fun){
        domObj.onclick = fun
    }
    this.focus = function (fun){
        domObj.onfocus = fun;
    }
    this.change = function (fun){
        domObj.onchange = fun;
    }
    this.blur = function (fun){
        domObj.onblur = fun;
    }
    this.val = function (v){
        if (v == undefined) {
            return domObj.value;
        }else {
            domObj.value = v;
        }
        return domObj.value;
    }

    // 静态的方法发送Ajax请求
    /**
     * 分析:使用ajax函数发送Ajax请求的时候 需要程序员给我们传过来什么?
     *     请求的方式:GET/POST
     *     请求的URL:url
     *     请求提交的数据:data
     *     请求时发送异步请求还是同步请求(async):true表示异步 false表示同步
     */
    jQuery.ajax = function (jsonArgs){
        var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function (){
                if (this.readyState == 4) {
                    if (this.status == 200) {
                        // 我们这个工具类在封装的时候 不考虑那么多 假设服务器返回的是json格式的字符串
                        var jsonObj = JSON.parse(this.responseText);
                        // 调用函数
                        jsonArgs.success(jsonObj);
                        // 返回的json格式数据:{"username":"zhangsan"}
                        // document.getElementById("mydiv").innerHTML = jsonObj.username
                    }
                }
            }
        if (jsonArgs.type.toUpperCase() == "POST") {
            xhr.open("POST",jsonArgs.url,jsonArgs.async);
            xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            xhr.send(jsonArgs.data);
        }

        if (jsonArgs.type.toUpperCase() == "GET") {
            xhr.open("GET",jsonArgs.url+"?"+jsonArgs.data,jsonArgs.async);
            xhr.send()
        }

    }
}
$ = jQuery();

// 这里有个细节 执行这个目的是为了让静态ajax生效
new jQuery();
