using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using letmeknow_admin.Models;
using System.Windows.Media.Imaging;
using System.Collections.ObjectModel;

namespace letmeknow_admin
{
    class AppService
    {
        public static Boolean login(string username, string password)
        {
            return true;
        }

        public static Boolean changePassword(string oldPwd, string newPwd)
        {
            return true;
        }

        public static List<User> searchUser(string name)
        {
            var result = new List<User>();
            result.Add(new User(1, "testUser"));
            result.Add(new User(2, "testUser2"));
            return result;
        }

        public static List<User> searchUser(int UID)
        {
            var result = new List<User>();
            result.Add(new User(1, "testUser"));
            result.Add(new User(2, "testUser2"));
            return result;
        }

        public static BitmapImage getImage(string URI)
        {
            return new BitmapImage(new Uri("F:\\letmeknow\\admin\\letmeknow-admin\\img\\usericon.png", UriKind.Absolute));
        }

        public static User getUser(int UID)
        {
            User user = new User(UID, "testUser", new DateTime(2017, 6, 28, 15, 13, 0), UserStatus.DELETED, UserCategory.ADMINISTRATOR, "");
            return user;
        }

        public static void deleteUser(User user)
        {
            user.status = UserStatus.DELETED;
        }

        public static void recoverUser(User user)
        {
            user.status = UserStatus.NORMAL;
        }

        public static void banUser(User user)
        {
            user.status = UserStatus.BANNED;
        }

        public static void unblockUser(User user)
        {
            user.status = UserStatus.NORMAL;
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
    }
}
