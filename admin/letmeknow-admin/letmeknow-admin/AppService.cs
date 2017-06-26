using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using letmeknow_admin.Models;

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
            return result;
        }

        public static List<UserSearchResultItem> searchUser(int UID)
        {
            var result = new List<UserSearchResultItem>();
            result.Add(new UserSearchResultItem(1, "testUser"));
            return result;
        }
    }
}
