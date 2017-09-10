using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using letmeknow_admin.Models;
using System.Windows.Media.Imaging;
using System.Collections.ObjectModel;


namespace letmeknow_admin.Services
{
    class GroupService
    {
        private static string attr = GeneralSetting.host + "houses/";

        public static List<Group> searchGroup(string name)
        {
            /*Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupName"] = name;
            parameters["start"] = "0";
            parameters["count"] = "32767";
            string JsonString = HttpHelper.Get(GeneralSetting.host + "fetchGroupByName", parameters);
            var result = JsonHelper.DeserializeJsonToObject<Dictionary<string, List<Group>>>(JsonString)["groups"];*/
            string JsonString = HttpHelper.Get(attr + "searchInfoByName/" + name);
            var result = JsonHelper.DeserializeJsonToList<Group>(JsonString);
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

        public static Group getGroup(string id)
        {
            /*Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupId"] = id.ToString();
            string JsonString = HttpHelper.Get(GeneralSetting.host + "groupDetail", parameters);
            var resultObj = new { message = string.Empty, group = new Group() };
            Group group = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).group;*/
            string JsonString = HttpHelper.Get(attr + "searchDetailById/" + id); ;
            Group group = JsonHelper.DeserializeJsonToObject<Group>(JsonString);
            return group;
        }       

        public static void deleteGroup(ref Group group)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupId"] = group.id;
            string JsonString = HttpHelper.Post(GeneralSetting.host + "deleteGroup", parameters);
            var resultObj = new { message = string.Empty, group = new Group() };
            group = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).group;
        }

        public static void recoverGroup(ref Group group)
        {
            /*Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupId"] = group.groupId.ToString();
            string JsonString = HttpHelper.Post(GeneralSetting.host + "restoreGroup", parameters);
            var resultObj = new { message = string.Empty, group = new Group() };
            group = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).group;*/
            string JsonString = HttpHelper.Put(attr + "activateHouse/" + group.id); ;
            group = JsonHelper.DeserializeJsonToObject<Group>(JsonString);
        }

        public static void banGroup(ref Group group)
        {
            /*Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupId"] = group.groupId.ToString();
            string JsonString = HttpHelper.Post(GeneralSetting.host + "disableGroup", parameters);
            var resultObj = new { message = string.Empty, group = new Group() };
            group = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).group;*/
            string JsonString = HttpHelper.Put(attr + "disableHouse/" + group.id); ;
            group = JsonHelper.DeserializeJsonToObject<Group>(JsonString);
        }

        public static void unblockGroup(ref Group group)
        {
            /*Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupId"] = group.groupId.ToString();
            string JsonString = HttpHelper.Post(GeneralSetting.host + "activateGroup", parameters);
            var resultObj = new { message = string.Empty, group = new Group() };
            group = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).group;*/
            string JsonString = HttpHelper.Put(attr + "activateHouse/" + group.id); ;
            group = JsonHelper.DeserializeJsonToObject<Group>(JsonString);
        }

        public static void toPublic(ref Group group)
        {
            /*Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupId"] = group.groupId.ToString();
            string JsonString = HttpHelper.Post(GeneralSetting.host + "setGroupToPublic", parameters);
            var resultObj = new { message = string.Empty, group = new Group() };
            group = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).group;*/
            string JsonString = HttpHelper.Put(attr + "setToPublic/" + group.id); ;
            group = JsonHelper.DeserializeJsonToObject<Group>(JsonString);
        }

        public static void toPrivate(ref Group group)
        {
            /*Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupId"] = group.groupId.ToString();
            string JsonString = HttpHelper.Post(GeneralSetting.host + "setGroupToPrivate", parameters);
            var resultObj = new { message = string.Empty, group = new Group() };
            group = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).group;*/
            string JsonString = HttpHelper.Put(attr + "setToPrivate/" + group.id); ;
            group = JsonHelper.DeserializeJsonToObject<Group>(JsonString);
        }

        public static void deleteIcon(ref Group group)
        {
            /*Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupId"] = group.groupId.ToString();
            string JsonString = HttpHelper.Post(GeneralSetting.host + "deleteGroupIcon", parameters);
            var resultObj = new { message = string.Empty, group = new Group() };
            group = JsonHelper.DeserializeAnonymousType(JsonString, resultObj).group;*/
            string JsonString = HttpHelper.Put(attr + "censorSigil/" + group.id); ;
            group = JsonHelper.DeserializeJsonToObject<Group>(JsonString);
        }

        public static List<User> getGroupMembers(string id)
        {
            //Dictionary<string, string> parameters = new Dictionary<string, string>();
            //parameters["groupId"] = id.ToString();
            string JsonString = HttpHelper.Get(attr + id + "/members/allMemberInfo");
            return JsonHelper.DeserializeJsonToList<User>(JsonString);
        }
    }
}
