using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace letmeknow_admin.Models
{
    enum UserStatus { NORMAL = 2, BANNED = 1, DELETED = 0};
    enum UserCategory { ADMINISTRATOR = 1, USER = 0};
    enum UserRole { MEMBER = 0, ADMIN = 1, MASTER = 2};

    class User
    {
        public int userId { set; get; }
        public string username { set; get; }
        public DateTime created_at { set; get; }
        public UserStatus status { set; get; }
        public UserCategory is_admin { set; get; }
        public string avatar { set; get; }
        public string email { set; get; }
        public string phone_num { set; get; }
        public UserRole role { set; get; }

        public User()
        {
        }

        public User(int userId, string name)
        {
            this.userId = userId;
            this.username = username;
        }

        public User(int userId, string username, DateTime created_at, UserStatus status, 
            UserCategory is_admin, string avatar, string email, string phone_num)
        {
            this.userId = userId;
            this.username = username;
            this.created_at = created_at;
            this.status = status;
            this.is_admin = is_admin;
            this.avatar = avatar;
            this.email = email;
            this.phone_num = phone_num;
        }
    }
}
