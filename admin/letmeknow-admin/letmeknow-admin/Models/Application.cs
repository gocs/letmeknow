using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace letmeknow_admin.Models
{
    class Application
    {
        public string id { set; get; }
        public string manId { set; get; }
        public string manName { set; get; }
        public string content { set; get; }
        public string houseId { set; get; }
        public string houseName { set; get; }
        public string createdDateString { set; get; }
    }
}
