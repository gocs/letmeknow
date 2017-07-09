using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace letmeknow_admin.Models
{
    public enum GroupStatus { NORMAL = 2, BANNED = 1, DELETED = 0 };
    public enum GroupCategory { PRIVATE = 0, PUBLIC = 1 }; 

    public class Group
    {
        public int groupId { get; set; }
        public string groupName { get; set; }
        public DateTime createdAt { get; set; }
        public string icon { get; set; }
        public GroupStatus status { get; set; }
        public GroupCategory category { get; set; }
        public int members { get; set; }
        public string master { get; set; }
    }
}
