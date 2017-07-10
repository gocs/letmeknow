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
    }
}
