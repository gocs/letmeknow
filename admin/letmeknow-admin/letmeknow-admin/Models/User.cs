using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace letmeknow_admin.Models
{
    enum UserStatus { NORMAL = 0, BANNED = 1, DELETED = 2};
    enum UserCategory { ADMINISTRATOR = 1, USER = 0};
    enum UserRole { MEMBER = 1, ADMIN = 1, MASTER = 2};

    class User
    {
        public string id { set; get; }
        public string username { set; get; }
        public string createdDateString { set; get; }
        public UserStatus status { set; get; }
        public bool admin { set; get; }
        public string avatar { set; get; }
        public string email { set; get; }
        public string phoneNumber { set; get; }
        public UserRole role { set; get; }

        public User()
        {
        }

        public User(string id, string name)
        {
            this.id = id;
            this.username = username;
        }

    }
}
