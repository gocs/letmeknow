using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using letmeknow_admin.Services;

namespace letmeknow_admin.Models
{
    public enum ComplaintCategory { USER = 2, GROUP = 1, NOTIFICATION = 0 };

    class Complaint
    {
        public string id { set; get; }
        public string whistleblowerId { set; get; }
        public string userId { set; get; }
        public string ravenId { set; get; }
        public string groupId { set; get; }
        public string description { set; get; }
        public string whistleblowerName { set; get; }
        public string username { set; get; }
        public string groupName { set; get; }

        public string ravenSender
        {
            get
            {
                var t = NotificationService.GetNotificationById(ravenId);
                return UserService.getUser(t[0].addresserId).username;
            }
        }
        public string categoryName { set; get; }
        public ComplaintCategory category { set; get; }
        public string createdDateString { set; get; }
    }
}
