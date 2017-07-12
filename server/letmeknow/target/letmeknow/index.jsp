<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
<h2>Hello World!</h2>
<p>
    latest update:7/12 10:00
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
<p>/groupDetail /groupMembers /deleteGroupIcon</p>
<p>params:groupId</p>
<p> return: 0 for invalid groupId | 1 for success</p>
<p>/setGroupToPublic /setGroupToPrivate</p>
<p>params:groupId</p>
<p> return: 0 for invalid groupId | 1 for success</p>
<p>/disableGroup /activateGroup /deleteGroup /restoreGroup</p>
<p>params:groupId</p>
<p> return: 0 for invalid groupId | 1 for success</p>
<p>/allComplaints</p>
<p>return:a list of currently UNCLOSED complaints</p>
<p>/closeComplaint</p>
<p>params:reportId</p>
<p>return: 0 for invalid complaint ID, 1 for success</p>
<p>/allApplications</p>
<p> return:a list of currently UNCLOSED applications to making a group public</p>
<p>/passApplication /denyApplication</p>
<p>params:applicationId</p>
<p>return:0 for invalid application ID, 1 for success</p>
<p>/lougout</p>
<form action="QuickLogin">
<button type="submit"> auto login </button>
</form>

</body>
</html>
