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
using System.Drawing;

namespace letmeknow_admin
{
    /// <summary>
    /// UserInGroup.xaml 的交互逻辑
    /// </summary>
    public partial class UserInGroup : MetroWindow
    {
        public UserInGroup(Group group)
        {
            InitializeComponent();
            dataGrid.ItemsSource = AppService.getGroupMembers(group.groupId);
            lblTitle.Content = string.Format("\"{0}\"的成员", group.groupName);
            dataGrid.Loaded += (sender, e) => bindActionToRows();
            dataGrid.Sorted += (sender, e) => bindActionToRows();
        }

        private void bindActionToRows()
        {
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
                    if ((row.Item as User).role == UserRole.MASTER)
                    {
                        row.FontWeight = FontWeights.Bold;
                        row.Foreground = System.Windows.Media.Brushes.Blue;
                    }
                    else if ((row.Item as User).role == UserRole.ADMIN)
                        row.FontWeight = FontWeights.Bold;
                    row.MouseDoubleClick += (_sender, _e) =>
                    {
                        UserDetail userDetail = new UserDetail((((DataGridRow)_sender).Item as Models.User).userId);
                        userDetail.Show();
                    };
                }
            }
        }
    }
}
