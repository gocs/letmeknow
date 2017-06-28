using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace letmeknow_admin.Models
{
    public class NotificationSearchResultItem
    {
        public int id { set; get; }
        public DateTime time { set; get; }
        public string content { set; get; }

        public NotificationSearchResultItem(int id, DateTime time, string content)
        {
            this.id = id;
            this.time = time;
            this.content = content;
        }
    }
}
