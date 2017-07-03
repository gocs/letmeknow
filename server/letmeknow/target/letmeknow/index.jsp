<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<body>
<h2>Hello World!</h2>
<p>
latest update:7/3 17:00
</p>


current APIs available:
<p>/adminlogin</p>
<p>    params:username password</p>
<p>   return: 0 for faulty username/password | 1 for no privilege | 2 for success</p>
<p>/userdetail  /promotePrivilege  /reducePrivilege  /disableUser  /activateUser  /deleteUser  /restoreUser  /deleteAvatar</p>
<p>    params:userId</p>
<p>   return: 0 for invalid userId | 1 for success</p>
<p>/allGroups /allUsers /FetchGroupByName /FetchUserByName /FetchGroupById /FetchUserById</p>
<p> return: a list of current groups/users available</p>
</body>
</html>
