using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace letmeknow_admin.Models
{
    enum UserStatus { NORMAL, BANNED, DELETED};
    enum UserCategory { ADMINISTRATOR, USER};

    class User
    {
        public int UID { set; get; }
        public string name { set; get; }
        public DateTime registerTime { set; get; }
        public UserStatus status { set; get; }
        public UserCategory category { set; get; }
        public string iconURL { set; get; }

        public User(int UID, string name, DateTime registerTime, UserStatus status, 
            UserCategory category, string iconURL)
        {
            this.UID = UID;
            this.name = name;
            this.registerTime = registerTime;
            this.status = status;
            this.category = category;
            this.iconURL = iconURL;
        }
    }
}
