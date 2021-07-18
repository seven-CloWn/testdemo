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
