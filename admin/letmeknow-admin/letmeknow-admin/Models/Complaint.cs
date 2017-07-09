using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace letmeknow_admin.Models
{
    public enum ComplaintCategory { USER, GROUP, NOTIFICATION};

    class Complaint
    {
        public int reportId { set; get; }
        public int reporterId { set; get; } 
        public int? userId { set; get; }
        public int? notificationId { set; get; }
        public int? groupId { set; get; }
        public string content { set; get; }
        public string reporterName { set; get; }
        public string username { set; get; }
        public string groupName { set; get; }
        public string notificationSender { set; get; }
        public string categoryName { set; get; }
        public ComplaintCategory category { set; get; }
        public DateTime createdAt { set; get; }
    }
}
