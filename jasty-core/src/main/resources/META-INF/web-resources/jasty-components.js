/**
 * Standard jasty components library
 *
 * @author Stanislav Tkachev
 * @version 1.0
 *
 */
jasty.JQuery = jasty.extend(jasty.Control, {
	text: function(self, value) {
		self.text(value);
	},

	html: function(self, value) {
		jasty.render(value, function(html) {
		    self.html(html);
		});
	},

	append: function(self, content) {
	    jasty.render(content, function(html) {
    		self.append(html);
	    })
	},

	empty: function(self) {
	    self.empty();
	}
});

jasty.Link = jasty.extend(jasty.Control, {

	init: function(self, opts) {
		self.attr("href", "#");
		self.text(opts.text);
		self.click(function() {
			jasty.raiseEvent(self, opts.onClick, {srcId: self.attr("id"), data: opts.data});
			return false;
		});
	},

	text: function(self, value) {
		self.text(value);
	}
});

jasty.Button = jasty.extend(jasty.Control, {

	init: function(self, opts) {
		self.val(opts.text);
		self.click(function() {
			jasty.raiseEvent(self, opts.onClick, {srcId: self.attr("id"), data: opts.data});
			return false;
		});
	},

	text: function(self, value) {
		self.val(value);
	}
});


jasty.TextBox = jasty.extend(jasty.Control, {
	init: function(self, opts) {
		self.attr("name", opts.id);
		self.val(opts.value);
	},

	value: function(self, value) {
		self.val(value);
	}
});

jasty.CheckBox = jasty.extend(jasty.Control, {
	init: function(self, opts) {
		self.attr("name", opts.id);
		if(opts.checked)
		    self.attr("checked", "checked");
		if(opts.onChange)
            self.change(function() {
                jasty.raiseEvent(self, opts.onChange, {srcId: self.attr("id"),
                    data: opts.data,
                    value: self.attr("checked") == "checked"});
                return false;
            });
	},

	checked: function(self, value) {
	    if(value)
		    self.attr("checked", "checked");
		else
		    self.removeAttr("checked");
	}
});

jasty.ComboBox = jasty.extend(jasty.Control, {
	init: function(self, opts) {
	    this.parent.init(self, opts);
		self.attr("name", opts.id);
		if(opts.options)
		    this.options(self, opts.options);
		if(opts.value)
		    self.val(opts.value);
		if(opts.onChange)
            self.change(function() {
                jasty.raiseEvent(self, opts.onChange, {srcId: self.attr("id"),
                    data: opts.data,
                    value: self.val()});
                return false;
            });
	},

	value: function(self, value) {
		self.val(value);
	},

	options: function(self, options) {
	    var oldValue = self.val();
	    self.empty();
	    $.each(options, function() {
	        $("<option/>").attr("value", this[0]).text(this[1]).appendTo(self);
	    });
	    self.val(oldValue);
	}
});
