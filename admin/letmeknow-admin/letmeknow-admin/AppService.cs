using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using letmeknow_admin.Models;
using System.Windows.Media.Imaging;
using System.Collections.ObjectModel;
using Newtonsoft.Json.Linq;

namespace letmeknow_admin
{
    class AppService
    {
        public enum LoginResult { SUCCESS = 2, WRONG = 0, NOAUTH = 1 };

        public static LoginResult login(string username, string password)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["username"] = username;
            parameters["password"] = password;
            string JsonString = HttpHelper.Post(GeneralSetting.host + "login", parameters);
            var result = (LoginResult)int.Parse(JObject.Parse(JsonString)["message"].ToString());
            return result;
        }

        public static Boolean changePassword(string oldPwd, string newPwd)
        {
            return true;
        }

        public static List<User> searchUser(string name)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["username"] = name;
            parameters["start"] = "0";
            parameters["count"] = "32767";
            string JsonString = HttpHelper.Get(GeneralSetting.host + "fetchUserByName", parameters);
            var result = JsonHelper.DeserializeJsonToObject<Dictionary<string, List<User>>>(JsonString)["users"];
            return result;
        }

        public static List<User> searchUser(int UID)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["userId"] = UID.ToString();
            string JsonString = HttpHelper.Get(GeneralSetting.host + "fetchUserById", parameters);
            var result = JsonHelper.DeserializeJsonToObject<Dictionary<string, List<User>>>(JsonString)["users"];
            return result;
        }

        public static List<Group> searchGroup(string name)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupName"] = name;
            parameters["start"] = "0";
            parameters["count"] = "32767";
            string JsonString = HttpHelper.Get(GeneralSetting.host + "fetchGroupByName", parameters);
            var result = JsonHelper.DeserializeJsonToObject<Dictionary<string, List<Group>>>(JsonString)["groups"];
            return result;
        }

        public static List<Group> searchGroup(int id)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupId"] = id.ToString();
            string JsonString = HttpHelper.Get(GeneralSetting.host + "fetchGroupById", parameters);
            var result = JsonHelper.DeserializeJsonToObject<Dictionary<string, List<Group>>>(JsonString)["groups"];
            return result;
        }

        public static BitmapImage getImage(string url)
        {
            if (url == null) return null;
            var re = new BitmapImage();
            var ss = HttpHelper.GetStream(GeneralSetting.host + "../" + url, "");
            re.BeginInit();
            re.StreamSource = ss;
            re.EndInit();
            re.DownloadCompleted += (sender, e) => 
                re.StreamSource.Close();            
            return re;
        }

        public static User getUser(int UID)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["userId"] = UID.ToString();
            string JsonString = HttpHelper.Get(GeneralSetting.host + "userDetail", parameters);
            var resultObj = new { message = string.Empty, user = new User() };
            User user = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).user;
            return user;
        }

        public static Group getGroup(int id)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupId"] = id.ToString();
            string JsonString = HttpHelper.Get(GeneralSetting.host + "groupDetail", parameters);
            var resultObj = new { message = string.Empty, group = new Group() };
            Group group = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).group;
            return group;
        }

        public static void deleteUser(ref User user)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["userId"] = user.userId.ToString();
            string JsonString = HttpHelper.Post(GeneralSetting.host + "deleteUser", parameters);
            var resultObj = new { message = string.Empty, user = new User() };
            user = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).user;
        }

        public static void recoverUser(ref User user)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["userId"] = user.userId.ToString();
            string JsonString = HttpHelper.Post(GeneralSetting.host + "restoreUser", parameters);
            var resultObj = new { message = string.Empty, user = new User() };
            user = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).user;
        }

        public static void banUser(ref User user)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["userId"] = user.userId.ToString();
            string JsonString = HttpHelper.Post(GeneralSetting.host + "disableUser", parameters);
            var resultObj = new { message = string.Empty, user = new User() };
            user = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).user;
        }

        public static void unblockUser(ref User user)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["userId"] = user.userId.ToString();
            string JsonString = HttpHelper.Post(GeneralSetting.host + "activateUser", parameters);
            var resultObj = new { message = string.Empty, user = new User() };
            user = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).user;
        }

        public static void deleteGroup(ref Group group)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupId"] = group.groupId.ToString();
            string JsonString = HttpHelper.Post(GeneralSetting.host + "deleteGroup", parameters);
            var resultObj = new { message = string.Empty, group = new Group() };
            group = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).group;
        }

        public static void recoverGroup(ref Group group)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupId"] = group.groupId.ToString();
            string JsonString = HttpHelper.Post(GeneralSetting.host + "restoreGroup", parameters);
            var resultObj = new { message = string.Empty, group = new Group() };
            group = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).group;
        }

        public static void banGroup(ref Group group)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupId"] = group.groupId.ToString();
            string JsonString = HttpHelper.Post(GeneralSetting.host + "disableGroup", parameters);
            var resultObj = new { message = string.Empty, group = new Group() };
            group = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).group;
        }

        public static void unblockGroup(ref Group group)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupId"] = group.groupId.ToString();
            string JsonString = HttpHelper.Post(GeneralSetting.host + "activateGroup", parameters);
            var resultObj = new { message = string.Empty, group = new Group() };
            group = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).group;
        }

        public static ObservableCollection<Notification> SearchNotificationByUser(int UID)
        {
            var SearchResult = new ObservableCollection<Notification>();
            SearchResult.Add(new Notification(1, new DateTime(2017, 6, 28, 17, 11, 0), 1, "testUser", 1, "testGroup", "testContent", NotificationStatus.NORMAL));
            SearchResult.Add(new Notification(2, new DateTime(2017, 6, 28, 17, 12, 0), 1, "testUser", 1, "testGroup", "testContent", NotificationStatus.NORMAL));
            return SearchResult;
        }


        public static ObservableCollection<Notification> SearchNotificationByGroup(int id)
        {
            var SearchResult = new ObservableCollection<Notification>();
            SearchResult.Add(new Notification(1, new DateTime(2017, 6, 28, 17, 11, 0), 1, "testUser", 1, "testGroup", "testContent", NotificationStatus.NORMAL));
            SearchResult.Add(new Notification(2, new DateTime(2017, 6, 28, 17, 12, 0), 1, "testUser", 1, "testGroup", "testContent", NotificationStatus.NORMAL));
            return SearchResult;
        }

        public static ObservableCollection<Notification> SearchNotificationByKeyword(string key)
        {
            var SearchResult = new ObservableCollection<Notification>();
            SearchResult.Add(new Notification(1, new DateTime(2017, 6, 28, 17, 11, 0), 1, "testUser", 1, "testGroup", key, NotificationStatus.NORMAL));
            SearchResult.Add(new Notification(2, new DateTime(2017, 6, 28, 17, 12, 0), 1, "testUser", 1, "testGroup", key, NotificationStatus.NORMAL));
            return SearchResult;
        }

        public static void deleteNotification(Notification notification)
        {
            notification.status = NotificationStatus.DELETED;
        }

        public static void toAdmin(ref User user)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["userId"] = user.userId.ToString();
            string JsonString = HttpHelper.Post(GeneralSetting.host + "promotePrivilege", parameters);
            var resultObj = new { message = string.Empty, user = new User() };
            user = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).user;
        }

        public static void toNormalUser(ref User user)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["userId"] = user.userId.ToString();
            string JsonString = HttpHelper.Post(GeneralSetting.host + "reducePrivilege", parameters);
            var resultObj = new { message = string.Empty, user = new User() };
            user = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).user;
        }

        public static void toPublic(ref Group group)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupId"] = group.groupId.ToString();
            string JsonString = HttpHelper.Post(GeneralSetting.host + "setGroupToPublic", parameters);
            var resultObj = new { message = string.Empty, group = new Group() };
            group = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).group;
        }

        public static void toPrivate(ref Group group)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupId"] = group.groupId.ToString();
            string JsonString = HttpHelper.Post(GeneralSetting.host + "setGroupToPrivate", parameters);
            var resultObj = new { message = string.Empty, group = new Group() };
            group = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).group;
        }

        public static void deleteIcon(ref User user)      
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["userId"] = user.userId.ToString();
            string JsonString = HttpHelper.Post(GeneralSetting.host + "deleteAvatar", parameters);
            var resultObj = new { message = string.Empty, user = new User() };
            user = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).user;
        }

        public static void deleteIcon(ref Group group)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupId"] = group.groupId.ToString();
            string JsonString = HttpHelper.Post(GeneralSetting.host + "deleteGroupIcon", parameters);
            var resultObj = new { message = string.Empty, group = new Group() };
            group = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).group;
        }

        public static List<User> getGroupMembers(int id)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupId"] = id.ToString();
            string JsonString = HttpHelper.Get(GeneralSetting.host + "groupMembers", parameters);
            return JsonHelper.DeserializeJsonToObject<Dictionary<string, List<User>>>(JsonString)["members"];
        }

        public static ObservableCollection<Complaint> getAllComplaints()
        {
            string JsonString = HttpHelper.Get(GeneralSetting.host + "allComplaints", string.Empty);
            return JsonHelper.DeserializeJsonToObject<Dictionary<string, ObservableCollection<Complaint>>>(JsonString)["complaints"];
        }

        public static void closeComplaint(Complaint complaint)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["reportId"] = complaint.reportId.ToString();
            HttpHelper.Post(GeneralSetting.host + "closeComplaint", parameters);
        }

        public static ObservableCollection<Application> getAllApplications()
        {
            string JsonString = HttpHelper.Get(GeneralSetting.host + "allApplications", string.Empty);
            return JsonHelper.DeserializeJsonToObject<Dictionary<string, ObservableCollection<Application>>>(JsonString)["applications"];
        }

        public static void approveApplication(Application application)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["applicationId"] = application.applicationId.ToString();
            HttpHelper.Post(GeneralSetting.host + "passApplication", parameters);
        }

        public static void rejectApplication(Application application)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["applicationId"] = application.applicationId.ToString();
            HttpHelper.Post(GeneralSetting.host + "denyApplication", parameters);
        }
    }
}
