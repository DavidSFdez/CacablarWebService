﻿//------------------------------------------------------------------------------
// <auto-generated>
//     Este código fue generado por una herramienta.
//     Versión de runtime:4.0.30319.42000
//
//     Los cambios en este archivo podrían causar un comportamiento incorrecto y se perderán si
//     se vuelve a generar el código.
// </auto-generated>
//------------------------------------------------------------------------------

namespace sdi3_204.Cli_NET.RattingsService {
    
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(Namespace="http://ejb.impl.business.sdi.uo/", ConfigurationName="RattingsService.RattingsService")]
    public interface RattingsService {
        
        // CODEGEN: El parámetro 'return' requiere información adicional de esquema que no se puede capturar con el modo de parámetros. El atributo específico es 'System.Xml.Serialization.XmlElementAttribute'.
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        [return: System.ServiceModel.MessageParameterAttribute(Name="return")]
        sdi3_204.Cli_NET.RattingsService.listByTripResponse listByTrip(sdi3_204.Cli_NET.RattingsService.listByTrip request);
        
        // CODEGEN: El parámetro 'arg0' requiere información adicional de esquema que no se puede capturar con el modo de parámetros. El atributo específico es 'System.Xml.Serialization.XmlElementAttribute'.
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        sdi3_204.Cli_NET.RattingsService.deleteResponse delete(sdi3_204.Cli_NET.RattingsService.delete request);
    }
    
    /// <comentarios/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.6.1064.2")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://ejb.impl.business.sdi.uo/")]
    public partial class rating : object, System.ComponentModel.INotifyPropertyChanged {
        
        private string commentField;
        
        private long idField;
        
        private bool idFieldSpecified;
        
        private long seatAboutTripIdField;
        
        private bool seatAboutTripIdFieldSpecified;
        
        private long seatAboutUserIdField;
        
        private bool seatAboutUserIdFieldSpecified;
        
        private long seatFromTripIdField;
        
        private bool seatFromTripIdFieldSpecified;
        
        private long seatFromUserIdField;
        
        private bool seatFromUserIdFieldSpecified;
        
        private int valueField;
        
        private bool valueFieldSpecified;
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
        public string comment {
            get {
                return this.commentField;
            }
            set {
                this.commentField = value;
                this.RaisePropertyChanged("comment");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
        public long id {
            get {
                return this.idField;
            }
            set {
                this.idField = value;
                this.RaisePropertyChanged("id");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool idSpecified {
            get {
                return this.idFieldSpecified;
            }
            set {
                this.idFieldSpecified = value;
                this.RaisePropertyChanged("idSpecified");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=2)]
        public long seatAboutTripId {
            get {
                return this.seatAboutTripIdField;
            }
            set {
                this.seatAboutTripIdField = value;
                this.RaisePropertyChanged("seatAboutTripId");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool seatAboutTripIdSpecified {
            get {
                return this.seatAboutTripIdFieldSpecified;
            }
            set {
                this.seatAboutTripIdFieldSpecified = value;
                this.RaisePropertyChanged("seatAboutTripIdSpecified");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=3)]
        public long seatAboutUserId {
            get {
                return this.seatAboutUserIdField;
            }
            set {
                this.seatAboutUserIdField = value;
                this.RaisePropertyChanged("seatAboutUserId");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool seatAboutUserIdSpecified {
            get {
                return this.seatAboutUserIdFieldSpecified;
            }
            set {
                this.seatAboutUserIdFieldSpecified = value;
                this.RaisePropertyChanged("seatAboutUserIdSpecified");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=4)]
        public long seatFromTripId {
            get {
                return this.seatFromTripIdField;
            }
            set {
                this.seatFromTripIdField = value;
                this.RaisePropertyChanged("seatFromTripId");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool seatFromTripIdSpecified {
            get {
                return this.seatFromTripIdFieldSpecified;
            }
            set {
                this.seatFromTripIdFieldSpecified = value;
                this.RaisePropertyChanged("seatFromTripIdSpecified");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=5)]
        public long seatFromUserId {
            get {
                return this.seatFromUserIdField;
            }
            set {
                this.seatFromUserIdField = value;
                this.RaisePropertyChanged("seatFromUserId");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool seatFromUserIdSpecified {
            get {
                return this.seatFromUserIdFieldSpecified;
            }
            set {
                this.seatFromUserIdFieldSpecified = value;
                this.RaisePropertyChanged("seatFromUserIdSpecified");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=6)]
        public int value {
            get {
                return this.valueField;
            }
            set {
                this.valueField = value;
                this.RaisePropertyChanged("value");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool valueSpecified {
            get {
                return this.valueFieldSpecified;
            }
            set {
                this.valueFieldSpecified = value;
                this.RaisePropertyChanged("valueSpecified");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="listByTrip", WrapperNamespace="http://ejb.impl.business.sdi.uo/", IsWrapped=true)]
    public partial class listByTrip {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://ejb.impl.business.sdi.uo/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public long arg0;
        
        public listByTrip() {
        }
        
        public listByTrip(long arg0) {
            this.arg0 = arg0;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="listByTripResponse", WrapperNamespace="http://ejb.impl.business.sdi.uo/", IsWrapped=true)]
    public partial class listByTripResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://ejb.impl.business.sdi.uo/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public sdi3_204.Cli_NET.RattingsService.rating @return;
        
        public listByTripResponse() {
        }
        
        public listByTripResponse(sdi3_204.Cli_NET.RattingsService.rating @return) {
            this.@return = @return;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="delete", WrapperNamespace="http://ejb.impl.business.sdi.uo/", IsWrapped=true)]
    public partial class delete {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://ejb.impl.business.sdi.uo/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public long arg0;
        
        public delete() {
        }
        
        public delete(long arg0) {
            this.arg0 = arg0;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="deleteResponse", WrapperNamespace="http://ejb.impl.business.sdi.uo/", IsWrapped=true)]
    public partial class deleteResponse {
        
        public deleteResponse() {
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface RattingsServiceChannel : sdi3_204.Cli_NET.RattingsService.RattingsService, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class RattingsServiceClient : System.ServiceModel.ClientBase<sdi3_204.Cli_NET.RattingsService.RattingsService>, sdi3_204.Cli_NET.RattingsService.RattingsService {
        
        public RattingsServiceClient() {
        }
        
        public RattingsServiceClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public RattingsServiceClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public RattingsServiceClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public RattingsServiceClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        sdi3_204.Cli_NET.RattingsService.listByTripResponse sdi3_204.Cli_NET.RattingsService.RattingsService.listByTrip(sdi3_204.Cli_NET.RattingsService.listByTrip request) {
            return base.Channel.listByTrip(request);
        }
        
        public sdi3_204.Cli_NET.RattingsService.rating listByTrip(long arg0) {
            sdi3_204.Cli_NET.RattingsService.listByTrip inValue = new sdi3_204.Cli_NET.RattingsService.listByTrip();
            inValue.arg0 = arg0;
            sdi3_204.Cli_NET.RattingsService.listByTripResponse retVal = ((sdi3_204.Cli_NET.RattingsService.RattingsService)(this)).listByTrip(inValue);
            return retVal.@return;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        sdi3_204.Cli_NET.RattingsService.deleteResponse sdi3_204.Cli_NET.RattingsService.RattingsService.delete(sdi3_204.Cli_NET.RattingsService.delete request) {
            return base.Channel.delete(request);
        }
        
        public void delete(long arg0) {
            sdi3_204.Cli_NET.RattingsService.delete inValue = new sdi3_204.Cli_NET.RattingsService.delete();
            inValue.arg0 = arg0;
            sdi3_204.Cli_NET.RattingsService.deleteResponse retVal = ((sdi3_204.Cli_NET.RattingsService.RattingsService)(this)).delete(inValue);
        }
    }
}
