/**
 *
 * Created by wung on 2016/12/15.
 */

function login() {
    var loginName = $("#username").val().replace(/^\s*/, "").replace(/\s*$/, "");
    var password = $("#password").val().replace(/^\s*/, "").replace(/\s*$/, "");
    if (loginName == "") {
        $("span").first().html("username is null!");
        return;
    }
    if (password == "") {
        $("span").first().html("password is null!");
        return;
    }

    $.ajax({
        url: "http://localhost:8080/springDemo/nv/user/login",
        type: "post",
        dataType: "jsonp",
        //crossDomain:true,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        jsonp: "jsonpcallback",
        data : {
            loginname: loginName,
            password:password
        },
        success: function (json) {
            if (json.result == "true") {
                window.location.href = "main.jsp";
            } else if (json.result == "error") {
                $("span").first().html("username or password is error!");
            }
            else {
                $("span").first().html("username is not exists!");
            }
        },
        error: function (xhr, status, error) {
            console.log(status);
        }
    });
}

function logout() {
    $.ajax({
        url : "http://localhost:8080/springDemo/logout",
        type : "post",
		data : {
			logoutRequest:"true"
		},
        dataType : "jsonp",
        jsonp : "jsonpcallback",
        success : function(json) {
            console.log("退出登录..");
            window.location.href = "login.jsp";
        }
    });
}

function saveUser() {
    var userName = $("input[name=username]").val().replace(/^\s*/, "").replace(/\s*$/, "");
    var age = $("input[name=age]").val().replace(/^\s*/, "").replace(/\s*$/, "");
    console.log(userName);
    console.log(age);
    if (userName == "") {
        $("#errorInfo").html("username is null!");
        return;
    }
    if (age == "") {
        $("#errorInfo").html("age is null!");
        return;
    }

    $.ajax({
        url: "http://localhost:8080/springDemo/nv/user/saveuser",
        type: "post",
        dataType: "jsonp",
        //crossDomain:true,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        jsonp: "jsonpcallback",
        data : {
            userName: userName,
            age: age
        },
        success: function (json) {
            if (json.result) {
                $("#errorInfo").html("Save success.")
            }
            else {
                $("#errorInfo").html("Save fail.")
            }
        },
        error: function (xhr, status, error) {
            console.log(status);
        }
    });
}
