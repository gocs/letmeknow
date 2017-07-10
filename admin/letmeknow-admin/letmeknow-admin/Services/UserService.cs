using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using letmeknow_admin.Models;

namespace letmeknow_admin.Services
{
    class UserService
    {
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
