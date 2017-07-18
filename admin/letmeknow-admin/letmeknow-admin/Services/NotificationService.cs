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
        public static ObservableCollection<Notification> SearchNotificationByUser(int UID)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["senderId"] = UID.ToString();
            string JsonString = HttpHelper.Get("searchNotificationBySenderId", parameters);
            var result = JsonHelper.DeserializeJsonToObject<Dictionary<string, ObservableCollection<Notification>>>(JsonString)["notifications"];
            return result;
        }

        public static ObservableCollection<Notification> SearchNotificationByGroup(int id)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["groupId"] = id.ToString();
            string JsonString = HttpHelper.Get("searchNotificationByGroupId", parameters);
            var result = JsonHelper.DeserializeJsonToObject<Dictionary<string, ObservableCollection<Notification>>>(JsonString)["notifications"];
            return result;
        }

        public static ObservableCollection<Notification> SearchNotificationByKeyword(string key)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["content"] = key;
            string JsonString = HttpHelper.Get("searchNotificationByContent", parameters);
            var result = JsonHelper.DeserializeJsonToObject<Dictionary<string, ObservableCollection<Notification>>>(JsonString)["notifications"];
            return result;
        }

        public static void deleteNotification(Notification notification)
        {
            notification.status = NotificationStatus.DELETED;
        }
    }
}
