using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;

namespace letmeknow_admin.Models
{
    public enum ReceiptCategory { TEXT, OPTIONS};

    public class Receipt
    {
        public string question { set; get; }
        public ReceiptCategory type { set; get; }
        public List<string> options { set; get; }
        public string str
        {
            get
            {
                if (type == ReceiptCategory.TEXT)
                    return question;
                else
                {
                    string re = String.Empty;
                    foreach (var i in options)
                    {
                        re += i + ";";
                    }
                    return re;
                }
            }
        }
    }
}
