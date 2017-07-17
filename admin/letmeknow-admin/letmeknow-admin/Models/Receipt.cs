using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace letmeknow_admin.Models
{
    public enum ReceiptCategory { TEXT, OPTIONS};

    public class Receipt
    {
        public string text { set; get; }
        public ReceiptCategory category { set; get; }
        public List<string> options { set; get; }
    }
}
