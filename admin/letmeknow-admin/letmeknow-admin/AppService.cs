using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using letmeknow_admin.Models;
using System.Windows.Media.Imaging;

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

        public static List<UserSearchResultItem> searchUser(string name)
        {
            var result = new List<UserSearchResultItem>();
            result.Add(new UserSearchResultItem(1, "testUser"));
            result.Add(new UserSearchResultItem(2, "testUser2"));
            return result;
        }

        public static List<UserSearchResultItem> searchUser(int UID)
        {
            var result = new List<UserSearchResultItem>();
            result.Add(new UserSearchResultItem(1, "testUser"));
            result.Add(new UserSearchResultItem(2, "testUser2"));
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

        public static List<NotificationSearchResultItem> SearchNotificationByUser(int UID)
        {
            var SearchResult = new List<NotificationSearchResultItem>();
            SearchResult.Add(new NotificationSearchResultItem(1, new DateTime(2017, 6, 28, 17, 11, 0), "testContent"));
            SearchResult.Add(new NotificationSearchResultItem(2, new DateTime(2017, 6, 28, 17, 12, 0), "testContent"));
            return SearchResult;
        }
    }
}
