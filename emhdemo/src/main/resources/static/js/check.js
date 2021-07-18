function check_password()
    {

        if(document.form.newPwd.value.length > 10)
        {
            alert("密码长度不超过10！");
            return false;
        }
		
		if(document.form.newPwd.value != document.form.newPwd2.value)
		{
			alert("请输入相同的密码！");
			return false;
		}
    }

function check_tid()
    {
		if(document.addnotice.tid.value.length > 7)
        {
            alert("编号长度不超过7！");
            return false;
        }

        var check = document.addnotice.tid.value;
        if(typeof check !== number){
			alert("请输入正确的编号！");
			return false;
        }
        
    }
