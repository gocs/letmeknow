using letmeknow_admin.Models;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace letmeknow_admin.Services
{
    class NotificationService
    {
        private static string attr = GeneralSetting.host + "ravens/";

        public static ObservableCollection<Notification> SearchNotificationByUser(string UID)
        {
            /*Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["senderId"] = UID.ToString();
            string JsonString = HttpHelper.Get(GeneralSetting.host + "searchNotificationBySenderId", parameters);
            var result = JsonHelper.DeserializeJsonToObject<Dictionary<string, ObservableCollection<Notification>>>(JsonString)["notifications"];
            return result;*/
            string JsonString = HttpHelper.Get(attr + "searchByAddresser/" + UID);
            if (JsonString == string.Empty) return new ObservableCollection<Notification>();
            return new ObservableCollection<Notification>(JsonHelper.DeserializeJsonToList<Notification>(JsonString));
        }

        public static ObservableCollection<Notification> SearchNotificationByGroup(string id)
        {
            //Dictionary<string, string> parameters = new Dictionary<string, string>();
            //parameters["groupId"] = id.ToString();
            string JsonString = HttpHelper.Get(attr + "searchByHouse/" + id);
            if (JsonString == string.Empty) return new ObservableCollection<Notification>();
            return new ObservableCollection<Notification>(JsonHelper.DeserializeJsonToList<Notification>(JsonString));
        }

        public static ObservableCollection<Notification> SearchNotificationByKeyword(string key)
        {
            /*Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["content"] = key;
            string JsonString = HttpHelper.Get(GeneralSetting.host + "searchNotificationByContent", parameters);
            var result = JsonHelper.DeserializeJsonToObject<Dictionary<string, ObservableCollection<Notification>>>(JsonString)["notifications"];
            return result;*/
            string JsonString = HttpHelper.Get(attr + "searchByDescription/" + key);
            if (JsonString == string.Empty) return new ObservableCollection<Notification>();
            return new ObservableCollection<Notification>(JsonHelper.DeserializeJsonToList<Notification>(JsonString));
        }

        public static ObservableCollection<Notification> GetNotificationById(string id)
        {
            string JsonString = HttpHelper.Get(attr + "getById/" + id);
            if (JsonString == string.Empty) return new ObservableCollection<Notification>();
            var t = JsonHelper.DeserializeJsonToObject<Notification>(JsonString);
            var re =  new ObservableCollection<Notification>();
            re.Add(t);
            return re;
        }

        public static void deleteNotification(Notification notification)
        {
            HttpHelper.Delete(attr + "deleteRaven/" + notification.id);
        }
    }
}
