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
        public enum LoginResult { SUCCESS = 2, WRONG = 0, NOAUTH = 1};

        public static LoginResult login(string username, string password)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["username"] = username;
            parameters["password"] = password;
            string JsonString = HttpHelper.Post(GeneralSetting.host + "adminlogin", parameters);
            var result = (LoginResult) int.Parse(JObject.Parse(JsonString)["message"].ToString());
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
            string JsonString = HttpHelper.Get(GeneralSetting.host + "username", parameters);
            var result = JsonHelper.DeserializeJsonToObject<Dictionary<string, List<User>>>(JsonString)["users"];
            return result;
        }

        public static List<User> searchUser(int UID)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["userId"] = UID.ToString();
            string JsonString = HttpHelper.Get(GeneralSetting.host + "userId", parameters);
            var result = JsonHelper.DeserializeJsonToObject<Dictionary<string, List<User>>>(JsonString)["users"];
            return result;
        }

        public static List<Group> searchGroup(string name)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupname"] = name;
            parameters["start"] = "0";
            parameters["count"] = "32767";
            string JsonString = HttpHelper.Get(GeneralSetting.host + "groupname", parameters);
            var result = JsonHelper.DeserializeJsonToObject<Dictionary<string, List<Group>>>(JsonString)["groups"];
            return result;
        }

        public static List<User> searchGroup(int id)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["userId"] = id.ToString();
            string JsonString = HttpHelper.Get(GeneralSetting.host + "groupId", parameters);
            var result = JsonHelper.DeserializeJsonToObject<Dictionary<string, List<User>>>(JsonString)["groups"];
            return result;
        }

        public static BitmapImage getImage(string url)
        {
            var re = new BitmapImage();
            var ss = HttpHelper.GetStream(GeneralSetting.host + url, "");
            re.BeginInit();
            re.StreamSource = ss;
            re.EndInit();
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

        public static ObservableCollection<Notification> SearchNotificationByUser(int UID)
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

        public static void deleteIcon(ref User user)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["userId"] = user.userId.ToString();
            string JsonString = HttpHelper.Post(GeneralSetting.host + "deleteAvatar", parameters);
            var resultObj = new { message = string.Empty, user = new User() };
            user = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).user;
        }
    }
}
