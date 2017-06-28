using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace letmeknow_admin.Models
{
    public enum NotificationStatus { NORMAL, DELETED};

    public class Notification
    {
        public int id { set; get; }
        public DateTime time { set; get; }
        public int user_id { set; get; }
        public string user_name { set; get; }
        public int group_id { set; get; }
        public string group_name { set; get; }
        public string content { set; get; }
        public NotificationStatus status { set; get; }

        public Notification(int id, DateTime time, int user_id, string user_name, 
            int group_id, string group_name, string content, NotificationStatus status)
        {
            this.id = id;
            this.time = time;
            this.user_id = user_id;
            this.user_name = user_name;
            this.group_id = group_id;
            this.group_name = group_name;
            this.content = content;
            this.status = status;
        }
    }
}
