using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace letmeknow_admin.Models
{
    public enum GroupStatus { NORMAL = 0, BANNED = 1, DELETED = 2 };
    public enum GroupCategory { PRIVATE = 0, PUBLIC = 1 }; 

    public class Group
    {
        public string id { get; set; }
        public string name { get; set; }
        public string createdDateString { get; set; }
        public string lastModifiedDateString { get; set; }
        public string sigil { get; set; }
        public GroupStatus status { get; set; }
        public bool publicity { get; set; }
        public int memberNumbers { get; set; }
        public string master { get; set; }
        public string founderId { get; set; }
        public int capacity { get; set; }
        public string description { get; set; }
        public List<string> tags { get; set; }
    }
}
