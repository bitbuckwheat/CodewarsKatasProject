class Attr:

    def __init__(self, parts):
        self.parts = parts

    def __set__(self, obj, value):
        raise AttributeError("Cannot change the value")

    def __get__(self, parts):
        p = [True if type(i) is int else False for i in parts]
        print(p)
        if len(parts) == 4 and parts[0] != 0 and all(p):
            # self = parts
            # print('self:', self)
            return parts
        else:
            raise ValueError


class IPv4Address():
    # parts = Attr(parts)

    def __init__(self, parts):
        """
        Creates an object representing an IPv4 address

        :param parts: a list of 4 integers
        """
        self.p = parts
        # self.parts = Attr(parts)
        self.__dict__['parts'] = Attr(parts)


print(IPv4Address([1, 2, 3, 4]))
# address = IPv4Address.from_string('1.2.3.4')
# address.parts = 'x'
# print(address.parts)
a = IPv4Address([1, 2, 3, 4])
# print(a.parts)
a.parts = 'x'
