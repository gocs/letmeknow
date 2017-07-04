<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
<h2>Hello World!</h2>
<p>
    latest update:7/4 11:00
</p>


current APIs available:
<h1>namespace: /admin</h1>
<p>/login</p>
<p> params:username password</p>
<p> return: 0 for faulty username/password | 1 for no privilege | 2 for success</p>
<p>/userDetail /promotePrivilege /reducePrivilege /disableUser /activateUser /deleteUser /restoreUser /deleteAvatar</p>
<p> params:userId</p>
<p> return: 0 for invalid userId | 1 for success</p>
<p>/allGroups /allUsers /fetchGroupByName /fetchUserByName /fetchGroupById /fetchUserById</p>
<p> return: a list of current groups/users available</p>
<p>/groupDetail</p>
<p>params:groupId</p>
<p> return: 0 for invalid groupId | 1 for success</p>

<form action="QuickLogin">
<button type="submit"> auto login </button type>
</form>

</body>
</html>
