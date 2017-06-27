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

namespace letmeknow_admin
{
    /// <summary>
    /// SearchUser.xaml 的交互逻辑
    /// </summary>
    public partial class SearchUser : MetroWindow
    {
        public SearchUser()
        {
            InitializeComponent();
        }

        private void tileSearchUserByUID_Click(object sender, RoutedEventArgs e)
        {
            dataGrid.ItemsSource = AppService.searchUser(int.Parse(usrInfo.Text));
        }

        private void tileSearchUserByName_Click(object sender, RoutedEventArgs e)
        {
            //var t = AppService.searchUser("123");
            dataGrid.ItemsSource = AppService.searchUser(usrInfo.Text);
            for (int i = 0; i < dataGrid.Items.Count; i++)
            {
                DataGridRow row = (DataGridRow)dataGrid.ItemContainerGenerator.ContainerFromIndex(i);
                if (row == null)
                {
                    dataGrid.UpdateLayout();
                    dataGrid.ScrollIntoView(dataGrid.Items[i]);
                    row = (DataGridRow)dataGrid.ItemContainerGenerator.ContainerFromIndex(i);
                }
                if (row != null)
                {
                    row.MouseDoubleClick += (_sender, _e) =>
                    {
                        MessageBox.Show((((DataGridRow)_sender).Item as Models.UserSearchResultItem).name);
                    };
                }
            }
        }
    }
}
