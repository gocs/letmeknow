using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;

namespace letmeknow_admin.Controls
{
    public class DataGridPlus : DataGrid
    {
        public DataGridPlus()
        {
            SetResourceReference(StyleProperty, typeof(DataGrid));
        }

        public static readonly RoutedEvent SortedEvent = 
            EventManager.RegisterRoutedEvent("Sorted", RoutingStrategy.Bubble, 
                typeof(RoutedEventHandler), typeof(DataGridPlus));

        public event RoutedEventHandler Sorted
        {
            add { AddHandler(SortedEvent, value); }
            remove { RemoveHandler(SortedEvent, value); }
        }

        void RaiseSortedEvent()
        {
            RoutedEventArgs newEventArgs = new RoutedEventArgs(DataGridPlus.SortedEvent);
            RaiseEvent(newEventArgs);
        }

        protected override void OnSorting(DataGridSortingEventArgs eventArgs)
        {
            base.OnSorting(eventArgs);
            RaiseSortedEvent();
        }
    }
}
