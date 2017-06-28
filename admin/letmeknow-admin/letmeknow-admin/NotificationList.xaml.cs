using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using MahApps.Metro.Controls;
using letmeknow_admin.Models;

namespace letmeknow_admin
{
    /// <summary>
    /// NotificationList.xaml 的交互逻辑
    /// </summary>
    public partial class NotificationList : MetroWindow
    {
        public NotificationList(string title, List<NotificationSearchResultItem> SearchResult)
        {
            InitializeComponent();
            dataGrid.ItemsSource = SearchResult;
            lblTitle.Content = title;
        }
    }
}
