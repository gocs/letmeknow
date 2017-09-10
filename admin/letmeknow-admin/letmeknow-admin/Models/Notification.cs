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
        public string id { set; get; }
        public string createdDateString { set; get; }
        public string updatedDateString { set; get; }
        public string addresserId { set; get; }
        public List<string> addresseeIds { set; get; }
        public string title { set; get; }
        public string addresserName { set; get; }
        public string houseId { set; get; }
        public string houseName { set; get; }
        public string description { set; get; }
        public NotificationStatus status { set; get; }
        public List<Receipt> optionPolls { set; get; }
    }
}
