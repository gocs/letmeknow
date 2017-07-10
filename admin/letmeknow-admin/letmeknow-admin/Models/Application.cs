using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace letmeknow_admin.Models
{
    class Application
    {
        public int applicationId { set; get; }
        public int applierId { set; get; }
        public string applierName { set; get; }
        public string content { set; get; }
        public int groupId { set; get; }
        public string groupName { set; get; }
        public DateTime createdAt { set; get; }
    }
}
