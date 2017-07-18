<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<%
    String path = request.getContextPath();
%>
<link href="<%=path%>/css/bootstrap.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="<%=path%>/css/bootstrap-theme.css"
      rel="stylesheet">
<body>
<h2>Hello World!</h2>
<p>
    latest update:7/14 14:00
</p>


current APIs available:
<div class="container">
    <h1>namespace:/common</h1>
    <table class="table table-striped table-bordered table-hover">
        <tbody>
        <tr>
            <td>allGroups</td>
            <td>groupDetail?groupId=</td>
            <td>groupMember?groupId=</td>
            <td>groupNotifier?groupId=</td>
        </tr>
        <tr>
            <td>groupMemberDetail?userId=</td>
            <td>groupMemberDetailList?groupId=</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>updateGroupIntroduction?groupId= introduction=</td>
            <td>updateGroupAvatar?groupId= avatar=</td>
            <td>updateGroupName?groupId= groupName=</td>
            <td>updateUsername?userId= username=</td>
        </tr>
        <tr>
            <td>updateUserEmail?userId= email=</td>
            <td>updateUserAvatar?userId= avatar=</td>
            <td>updateUserPhoneNum?userId= phoneNum=</td>
            <td>updateUserInstallationId?installationId= userId=?</td>
        </tr>
        <tr>
            <td>joinGroup?groupId=?</td>
            <td>addToGroup?groupId=? userId=[]</td>
            <td>quitGroup?groupId=?</td>
            <td>kickOutFromGroup?groupId= userId=[]</td>
        </tr>
        <tr>
            <td>saveNotification?sender_id= content= receiver_id=[]</td>
            <td>getNotification?notificationId=</td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>
</div>
<br/><br/>
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
<p>/logout</p>
<form action="QuickLogin">
    <button type="submit"> auto login</button>
</form>

</body>
</html>
